raise 'Patch already integrated into buildr code' unless Buildr::VERSION.to_s == '1.5.7'

module Buildr
  module JavadocPatch
    module ProjectExtension
      include Extension

      after_define do |project|
        project.doc.options.merge!('sourcepath' => project.compile.sources.join(File::PATH_SEPARATOR))
      end
    end
  end
end

class Buildr::Project
  include Buildr::JavadocPatch::ProjectExtension
end
